//package com.cg.model.dto;
//
//import com.cg.model.Category;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.experimental.Accessors;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//@NoArgsConstructor
//
//@Getter
//@Setter
//@Accessors(chain = true)
//public class CategoryDTO implements Validator {
//    private String id;
//    private String title;
//
//    public Category toCategory() {
//        return new Category()
//                .setId(id)
//                .setTitle(title);
//    }
//
//    public CategoryDTO(String id, String title) {
//        this.id = id;
//        this.title = title;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return false;
//    }
//
//    @Override
//    public void validate(Object o, Errors errors) {
//
//    }
//}
