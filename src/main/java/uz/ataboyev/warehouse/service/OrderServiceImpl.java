package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.entity.Order;
import uz.ataboyev.warehouse.entity.OrderItem;
import uz.ataboyev.warehouse.entity.Product;
import uz.ataboyev.warehouse.enums.CurrencyTypeEnum;
import uz.ataboyev.warehouse.enums.OrderType;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.payload.clientDtos.ClientDtoForPageable;
import uz.ataboyev.warehouse.repository.OrderItemRepository;
import uz.ataboyev.warehouse.repository.OrderRepository;
import uz.ataboyev.warehouse.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static uz.ataboyev.warehouse.service.base.BaseService.minus1;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ClientService clientService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final BaseService baseService;

    @Override
    public ApiResult<?> byOrder(Long whId, Long categoryId) {

        List<OptionResDto> productResDtoList = productService.getProductsForOptionByCategoryId(categoryId);
        List<OptionResDto> optionResDtoList = categoryService.getCategoryListForOption(whId);
        List<OptionResDto> clientResDtoList = clientService.getClientsForOption();

        OrderDTO result = new OrderDTO(
                OptionDTO.makeOptionDTO(optionResDtoList),
                OptionDTO.makeOptionDTO(productResDtoList),
                OptionDTO.makeOptionDTO(clientResDtoList)
        );

        return ApiResult.successResponse(result);
    }

    @Override
    public ApiResult<?> addOrder(SaveOrderDTO orderDTO) {

        //warehouse id, product id va client idlarni haqiqatdan bazada mavjudligini soradi
        checkingOrderDTO(orderDTO);

        List<OrderItemDto> orderItemDtoList = orderDTO.getOrderItemDtoList();

        Order order = Order.make(orderDTO);
        Order order1 = saveOrder(order);
        System.out.println(order1.getId());
        List<OrderItem> orderItemList = OrderItem.makeList(orderItemDtoList, order1.getId(), order1.getType());

        //SAVDODAGI BARCHA MAXSULOTLARNI NARHINI YIG'IBERADI SUM VA DOLLARNI ADDENNI QILIB
        OrderPriceDto orderPriceDto = calculationOrderPrice(orderItemList);
        saveOrder(order, orderPriceDto);

        orderItemListSaved(orderItemList);

        //PRODUCTLARNI BAZADAGI SONLARINI O'ZGARTIRIB SAQLAB QO'YDI
        editProductCount(orderDTO.getOrderType(), orderItemList);
        return ApiResult.successResponse(" Order muvaffaqiyatli saqlandi");
    }


    @Override
    public ApiResult<?> getAllOrder(SaveOrderDTO orderDTO) {
        return null;
    }

    @Override
    public List<CustomPage<OrderPageDTO>> getOrdersPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Order> orderPage = orderRepository.findAll(pageable);
        CustomPage<OrderPageDTO> orderPageDTOCustomPage = orderPageDTOCustomPage(orderPage);
        return List.of(orderPageDTOCustomPage);
    }

    @Override
    public OrderPriceDto generalPriceOrders(Long whId) {
        ArrayList<OrderPriceDto> list = new ArrayList<>();
        Long bossId = baseService.getBossId();
        Double sum, dollar;

        OrderPriceDtoForRep orderPriceDtoForRep = orderRepository.orderPriceByWhId(bossId, whId);
        dollar = orderPriceDtoForRep.getDollar() == null ? 0D : Double.parseDouble(orderPriceDtoForRep.getDollar());
        sum = orderPriceDtoForRep.getSum() == null ? 0D : Double.parseDouble(orderPriceDtoForRep.getSum());
        return OrderPriceDto.make(sum, dollar);
    }


//    --------------------------------- HELPER METHOD -----------------------------------------

    private CustomPage<OrderPageDTO> orderPageDTOCustomPage(Page<Order> orderPage) {

        List<OrderPageDTO> collect = new ArrayList<>();
        List<Order> orderList = orderPage.getContent();

        //HAR BIR ORDERNI ORDERPAGEGA O'GIRIB COLLECTGA YIG'IBERADI
        for (Order order : orderList) {

            //ORDER ICHIDAN CLIENTNI MALUMOTLARINI DTOGA ORABERADI
            ClientDtoForPageable clientDto = ClientDtoForPageable.make(order.getClient());

            collect.add(new OrderPageDTO(
                    order.getUpdatedAt(),
                    clientDto,
                    order.getOrderPriceSum(),
                    order.getOrderPriceDollar(),
                    order.getType()));
        }

        return new CustomPage<>(
                collect,
                orderPage.getNumberOfElements(),// Elementlar
                orderPage.getNumber(), // Current page dagi elementlar soni
                orderPage.getTotalElements(), // Current page number
                orderPage.getTotalPages(), // Barcha elementlar soni
                orderPage.getSize() //Barcha page lar soni
        );
    }

    private void editProductCount(OrderType orderType, List<OrderItem> orderItemList) {
        try {
            double a = 1D, databaseCount;
            ArrayList<Product> productList = new ArrayList<>();

            //AGAR ORDER CHIQIM BO'LSA
            if (orderType.name().equals("EXPENDITURE")) a = minus1;

            for (OrderItem orderItem : orderItemList) {

                Product product = baseService.getProductById(orderItem.getProductId());

                databaseCount = product.getCount() + a * orderItem.getCount();
                product.setCount(databaseCount);

                productList.add(product);
            }
            baseService.savedProductList(productList);

        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.restThrow("Mahsulotlarni sonini o'zgartirishda hatolik bo'ldi");
        }
    }

    private void checkingOrderDTO(SaveOrderDTO orderDTO) {

        if (!baseService.existsWarehouse(orderDTO.getWarehouseId()))
            throw RestException.restThrow("SAVDONI SAQLAMOQCHI BO'LGAN OMBORXONA TOPILMADI", HttpStatus.NOT_FOUND);

        List<Long> productIds = orderDTO.getOrderItemDtoList().stream().map(OrderItemDto::getProductId).collect(Collectors.toList());
        productService.checkingProductByIdListOrElseThrow(productIds);

        Long clientId = orderDTO.getClientId();
        clientService.checkingClientByIdListOrElseThrow(List.of(clientId));

    }

    private void orderItemListSaved(List<OrderItem> orderItemList) {
        try {

            orderItemRepository.saveAll(orderItemList);

        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.restThrow("Order Item no saved");
        }

    }

    private OrderPriceDto calculationOrderPrice(List<OrderItem> orderItemList) {
        Double sum = orderItemList.stream().filter(orderItem ->
                orderItem.getCurrencyType().equals(CurrencyTypeEnum.SUM)).
                mapToDouble(orderItem ->
                        orderItem.getCount() * orderItem.getAmount()).sum();
        Double dollar = orderItemList.stream().filter(orderItem ->
                orderItem.getCurrencyType().equals(CurrencyTypeEnum.DOLLAR)).
                mapToDouble(orderItem ->
                        orderItem.getCount() * orderItem.getAmount()).sum();
        return new OrderPriceDto(sum, dollar);
    }

    private void saveOrder(Order order, OrderPriceDto orderPriceDto) {
        try {
            order.setOrderPriceSum(orderPriceDto.getSum());
            order.setOrderPriceDollar(orderPriceDto.getDollar());
            orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.restThrow("Order no saved");
        }
    }

    private Order saveOrder(Order order) {
        try {

            return orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.restThrow("Order no saved");
        }
    }


}
