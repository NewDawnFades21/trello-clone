//package edu.zsc.todolistproject.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class WebMvcConfig extends WebMvcConfigurerAdapter {
//    // 获取配置文件中图片的路径
//    @Value("${cbs.imagesPath}")
//    private String mImagesPath;
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        if (mImagesPath.equals("") || mImagesPath.equals("${cbs.imagesPath}")) {
//            String imagesPath = WebMvcConfig.class.getClassLoader().getResource("").getPath();
//            if (imagesPath.indexOf(".jar") > 0) {
//                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
//            } else if (imagesPath.indexOf("classes") > 0) {
//                imagesPath = "file:" + imagesPath.substring(0, imagesPath.indexOf("classes"));
//            }
//            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/")) + "/uploads/";
//            mImagesPath = imagesPath;
//        }
//        registry.addResourceHandler("/uploads/**").addResourceLocations(mImagesPath);
//        super.addResourceHandlers(registry);
//    }
//
//
//}
