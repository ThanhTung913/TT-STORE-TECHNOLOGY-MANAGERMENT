//package com.cg.controller.rest;
//
//
//import com.cg.exception.DataInputException;
//import com.cg.model.Cart;
//import com.cg.model.CartItem;
//import com.cg.model.Product;
//import com.cg.model.dto.CartItemListDTO;
//import com.cg.model.dto.ProductCreateDTO;
//import com.cg.model.dto.ProductListDTO;
//import com.cg.model.dto.ProductRequestCartDTO;
//import com.cg.service.cart.CartService;
//import com.cg.service.cartItem.CartItemService;
//import com.cg.service.product.ProductService;
//import com.cg.utils.AppUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/products")
//public class ProductRestController {
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private CartService cartService;
//
//    @Autowired
//    private CartItemService cartItemService;
//
//    @Autowired
//    private AppUtils appUtils;
//
//
//    @GetMapping
//    public ResponseEntity<?> getAll() {
//
//        List<ProductListDTO> productListDTOS = productService.findAllProductListDTO();
//
//
//        return new ResponseEntity<>(productListDTOS, HttpStatus.OK);
//    }
//
//
//    @PostMapping("/create")
//    public ResponseEntity<?> create(@Validated ProductCreateDTO productCreateDTO, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors())
//            return appUtils.mapErrorToResponse(bindingResult);
//
//        try {
//            Product createdProduct = productService.create(productCreateDTO);
//
////            IProductDTO iProductDTO =  productService.findIProductDTOById(createdProduct.getId());
//
//            return new ResponseEntity<>(HttpStatus.CREATED);
//
//        } catch (DataIntegrityViolationException e) {
//            throw new DataInputException("Product creation information is not valid, please check the information again");
//        }
//    }
//
//
//    @PostMapping("/add-cart")
//    public ResponseEntity<?> addCart(@RequestBody ProductRequestCartDTO productRequestCartDTO) {
//
//        String createdBy = AppUtils.getPrincipalUsername();
//
//        System.out.println(createdBy);
//
//        Optional<Cart> cartOptional = cartService.findByCreatedBy(createdBy);
//
////        Boolean existsByCreatedBy = cartService.existsByCreatedBy(createdBy);
//
//        String productId = productRequestCartDTO.getId();
//
//        Optional<Product> product = productService.findById(productId);
//
//        if (!product.isPresent()) {
//            throw new DataInputException("Thông tin sản phẩm không hợp lệ");
//        }
//
//        if (!cartOptional.isPresent()) {
//            Cart cart = new Cart();
//            cart.setCreatedBy(Long.valueOf(createdBy));
//            cart.setTotalAmount(new BigDecimal(0L));
//
//            Cart newCard = cartService.save(cart);
//
//            CartItem cartItem = new CartItem();
//            cartItem.setId(0L);
//            cartItem.setProductId(productId);
//            cartItem.setTitle(product.get().getTitle());
//            cartItem.setPrice(product.get().getPrice());
//            cartItem.setQuantity(1);
//            cartItem.setAmount(product.get().getPrice());
//            cartItem.setCart(newCard);
//            cartItemService.save(cartItem);
//
//            Long cartId = newCard.getId();
//            newCard.setTotalAmount(cartItem.getPrice());
//            cartService.save(newCard);
//
//            List<CartItemListDTO> cartItemListDTOS = cartItemService.findAllCartItemsDTO(cartId);
//            return new ResponseEntity<>(cartItemListDTOS, HttpStatus.CREATED);
//        }
//
//        Optional<CartItem> cartItemOptional = cartItemService.findByProductId(productId);
//
//        if (!cartItemOptional.isPresent()) {
//            CartItem cartItem = new CartItem();
//            cartItem.setId(0L);
//            cartItem.setProductId(productId);
//            String productTitle = product.get().getTitle();
//            System.out.println(productTitle);
//            cartItem.setTitle(productTitle);
//            cartItem.setPrice(product.get().getPrice());
//            cartItem.setQuantity(1);
//            cartItem.setAmount(product.get().getPrice());
//            cartItem.setCart(cartOptional.get());
//            cartItemService.save(cartItem);
//
//            Long cartId = cartOptional.get().getId();
//            BigDecimal totalAmount = cartItemService.getSumAmountByCartId(cartId);
//            Cart cart = cartOptional.get();
//            cart.setTotalAmount(totalAmount);
//            cartService.save(cart);
//
//            List<CartItemListDTO> cartItemListDTOS = cartItemService.findAllCartItemsDTO(cartId);
//            return new ResponseEntity<>(cartItemListDTOS, HttpStatus.CREATED);
//        }
//
//        BigDecimal price = product.get().getPrice();
//        int oldQuantity = cartItemOptional.get().getQuantity();
//        int newQuantity = oldQuantity + 1;
//        BigDecimal newAmount = price.multiply(new BigDecimal(newQuantity));
//
//        CartItem cartItem = new CartItem();
//        cartItem.setId(cartItemOptional.get().getId());
//        cartItem.setProductId(productId);
//        cartItem.setTitle(product.get().getTitle());
//        cartItem.setPrice(product.get().getPrice());
//        cartItem.setQuantity(newQuantity);
//        cartItem.setAmount(newAmount);
//        cartItem.setCart(cartOptional.get());
//        cartItemService.save(cartItem);
//
//        BigDecimal totalAmount = cartItemService.getSumAmountByCartId(cartOptional.get().getId());
//
//
//        Long cartId = cartOptional.get().getId();
//
//        Cart cart = new Cart();
//        cart.setId(cartId);
//        cart.setCreatedBy(Long.valueOf(createdBy));
//        cart.setTotalAmount(totalAmount);
//        cartService.save(cart);
//
//        List<CartItemListDTO> cartItemListDTOS = cartItemService.findAllCartItemsDTO(cartId);
//        return new ResponseEntity<>(cartItemListDTOS, HttpStatus.CREATED);
//    }
//}
