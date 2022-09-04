//package com.cg.service.product;
//
//
//import com.cg.exception.DataInputException;
//import com.cg.model.Category;
//import com.cg.model.Product;
//import com.cg.model.ProductMedia;
//import com.cg.model.dto.ProductCreateDTO;
//import com.cg.model.dto.ProductListDTO;
//import com.cg.model.enums.FileType;
//import com.cg.repository.ProductMediaRepository;
//import com.cg.repository.ProductRepository;
//import com.cg.service.category.CategoryService;
//import com.cg.service.upload.UploadService;
//import com.cg.utils.UploadUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//@Service
//@Transactional
//public class ProductServiceImpl implements ProductService {
//
//    @Autowired
//    private CategoryService categoryService;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private ProductMediaRepository productMediaRepository;
//
//    @Autowired
//    private UploadService uploadService;
//
//    @Autowired
//    private UploadUtils uploadUtils;
//
//
//    @Override
//    public List<Product> findAll() {
//        return productRepository.findAll();
//    }
//
//    @Override
//    public List<ProductListDTO> findAllProductListDTO() {
//        return productRepository.findAllProductListDTO();
//    }
//
//    @Override
//    public Optional<Product> findById(Long id) {
//        return productRepository.findById(id);
//    }
//
//    @Override
//    public Optional<Product> findById(String id) {
//        return productRepository.findById(id);
//    }
//
//    @Override
//    public Product getById(Long id) {
//        return null;
//    }
//
//    @Override
//    public Product getById(String id) {
//        return null;
//    }
//
//
//    @Override
//    public Product create(ProductCreateDTO productCreateDTO) {
//        String fileType = productCreateDTO.getFile().getContentType();
//
//        assert fileType != null;
//
//        fileType = fileType.substring(0, 5);
//
//        productCreateDTO.setFileType(fileType);
//
//        Long categoryId = productCreateDTO.getCategoryId();
//
//        Optional<Category> category = categoryService.findById(categoryId);
//
//        Product product = productRepository.save(productCreateDTO.toProduct(category.get()));
//
//        ProductMedia productMedia = productMediaRepository.save(productCreateDTO.toProductMedia());
//
//        if (fileType.equals(FileType.IMAGE.getValue())) {
//            uploadAndSaveProductImage(productCreateDTO, product, productMedia);
//        }
//
//        if (fileType.equals(FileType.VIDEO.getValue())) {
//            uploadAndSaveProductVideo(productCreateDTO, product, productMedia);
//        }
//
//        return product;
//    }
//
//    private void uploadAndSaveProductImage(ProductCreateDTO productDTO, Product product, ProductMedia productMedia) {
//        try {
//            Map uploadResult = uploadService.uploadImage(productDTO.getFile(), uploadUtils.buildImageUploadParams(productMedia));
//            String fileUrl = (String) uploadResult.get("secure_url");
//            String fileFormat = (String) uploadResult.get("format");
//
//            productMedia.setFileName(productMedia.getId() + "." + fileFormat);
//            productMedia.setFileUrl(fileUrl);
//            productMedia.setFileFolder(UploadUtils.IMAGE_UPLOAD_FOLDER);
//            productMedia.setCloudId(productMedia.getFileFolder() + "/" + productMedia.getId());
//            productMedia.setProduct(product);
//            productMediaRepository.save(productMedia);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new DataInputException("Upload hình ảnh thất bại");
//        }
//    }
//
//    private void uploadAndSaveProductVideo(ProductCreateDTO productDTO, Product product, ProductMedia productMedia) {
//        try {
//            Map uploadResult = uploadService.uploadVideo(productDTO.getFile(), uploadUtils.buildVideoUploadParams(productMedia));
//            String fileUrl = (String) uploadResult.get("secure_url");
//            String fileFormat = (String) uploadResult.get("format");
//
//            productMedia.setFileName(productMedia.getId() + "." + fileFormat);
//            productMedia.setFileUrl(fileUrl);
//            productMedia.setFileFolder(UploadUtils.VIDEO_UPLOAD_FOLDER);
//            productMedia.setCloudId(productMedia.getFileFolder() + "/" + productMedia.getId());
//            productMedia.setProduct(product);
//            productMediaRepository.save(productMedia);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new DataInputException("Upload video thất bại");
//        }
//    }
//
//    @Override
//    public Product save(Product product) {
//        return null;
//    }
//
//    @Override
//    public void remove(Long id) {
//
//    }
//
//    @Override
//    public void remove(String id) {
//
//    }
//}
