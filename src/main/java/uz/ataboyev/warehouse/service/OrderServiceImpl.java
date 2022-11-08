package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.entity.Order;
import uz.ataboyev.warehouse.entity.OrderItem;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.repository.OrderItemRepository;
import uz.ataboyev.warehouse.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ClientService clientService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public ApiResult<?> byOrder() {

        List<ProductDto> productDtoList = productService.getAllProductListByCategoryId(1L);
        List<CategoryResDto> categoryResDtoList = categoryService.getAllCategoryList(1L);
        List<ClientResDto> clientResDtoList = clientService.getAll();

        OrderDTO result = new OrderDTO(
                OptionDTO.makeOptionDTO(categoryResDtoList),
                OptionDTO.makeOptionDTO(productDtoList),
                OptionDTO.makeOptionDTO(clientResDtoList)
        );

        return ApiResult.successResponse(result);
    }

    @Override
    public ApiResult<?> addOrder(SaveOrderDTO orderDTO) {

        checkingOrderDTO(orderDTO);

        List<OrderItemDto> orderItemDtoList = orderDTO.getOrderItemDtoList();

        Order order = Order.make(orderDTO);

        saveOrder(order);

        List<OrderItem> orderItemList = OrderItem.makeList(orderItemDtoList, order);

        orderItemListSaved(orderItemList);

        return ApiResult.successResponse("Muvaffaqiyatli saqlandi");
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


    @Override
    public ApiResult<?> getAllOrder(SaveOrderDTO orderDTO) {
        return null;
    }


}
