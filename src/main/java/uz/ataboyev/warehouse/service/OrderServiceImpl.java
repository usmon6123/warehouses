package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.ataboyev.warehouse.entity.Order;
import uz.ataboyev.warehouse.entity.OrderItem;
import uz.ataboyev.warehouse.entity.Product;
import uz.ataboyev.warehouse.enums.OrderType;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.repository.OrderItemRepository;
import uz.ataboyev.warehouse.repository.OrderRepository;
import uz.ataboyev.warehouse.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
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
    public ApiResult<?> byOrder(Long whId,Long categoryId) {

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
    @Transactional
    public ApiResult<?> addOrder(SaveOrderDTO orderDTO) {

        //product id va client idlarni haqiqatdan bazada mavjudligini soradi
        checkingOrderDTO(orderDTO);

        List<OrderItemDto> orderItemDtoList = orderDTO.getOrderItemDtoList();

        Order order = Order.make(orderDTO);

        saveOrder(order);

        List<OrderItem> orderItemList = OrderItem.makeList(orderItemDtoList, order);

        orderItemListSaved(orderItemList);

        //PRODUCTLARNI BAZADAGI SONLARINI O'ZGARTIRIB QO'YDI
        editProductCount(orderDTO.getOrderType(),orderItemList);

        return ApiResult.successResponse("Muvaffaqiyatli saqlandi");
    }

    @Override
    public ApiResult<?> getAllOrder(SaveOrderDTO orderDTO) {
        return null;
    }


//    public ApiResult<CustomPage<SpecializationDto>> getAll(int page, int size) {
//        Pageable pageable= PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
//        Page<Specialization> specializationPage=specializationRepository.findAll(pageable);
//        CustomPage<SpecializationDto> specializationDtoCustomPage=specializationDtoCustomPage(specializationPage);
//        return ApiResult.successResponse(specializationDtoCustomPage);
//    }













    private void editProductCount(OrderType orderType, List<OrderItem> orderItemList) {
        try {
            double a = 1D, databaseCount;
            ArrayList<Product> productList = new ArrayList<>();
            //AGAR ORDER CHIQIM BO'LSA
            if (orderType.name().equals("EXPENDITURE"))    a=minus1;

            for (OrderItem orderItem : orderItemList) {

                Product product = baseService.getProductById(orderItem.getProductId());

                databaseCount = product.getCount()+a*orderItem.getCount();
                product.setCount(databaseCount);

                productList.add(product);
            }
            baseService.savedProductList(productList);

        }catch (Exception e){
            e.printStackTrace();
            throw RestException.restThrow("Mahsulotlarni sonini o'zgartirishda hatolik bo'ldi");
        }
    }

    private void checkingOrderDTO(SaveOrderDTO orderDTO) {

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

    private void saveOrder(Order order) {

        try {
            orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.restThrow("Order no saved");
        }

    }
}
