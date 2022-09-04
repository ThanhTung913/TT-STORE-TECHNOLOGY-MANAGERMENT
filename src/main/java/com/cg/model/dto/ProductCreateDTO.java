package com.cg.model.dto;

import com.cg.model.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductCreateDTO implements Serializable {

    private String id;

    @NotBlank(message = "Title is required")
    private String title;

    private BigDecimal price;

    private Long categoryId;

    private String fileName;

    private String fileFolder;

    private String fileUrl;

    private String cloudId;

    private String fileProductId;

    private MultipartFile file;

    private String fileType;

    public Product toProduct(Category category) {
        return new Product()
                .setId(id)
                .setTitle(title)
                .setPrice(price)
                .setCategory(category);
    }

    public ProductMedia toProductMedia() {
        return new ProductMedia()
                .setId(fileProductId)
                .setFileName(fileName)
                .setFileFolder(fileFolder)
                .setFileUrl(fileUrl)
                .setCloudId(cloudId)
                .setFileType(fileType);
    }
}
