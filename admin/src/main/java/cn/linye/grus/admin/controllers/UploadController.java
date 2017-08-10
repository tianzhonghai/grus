package cn.linye.grus.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * 参考http://blog.csdn.net/a625013/article/details/52414470
 * Created by Tim on 2017/8/10.
 */
@Controller
@RequestMapping("/upload/")
public class UploadController {
    public static final String ROOT_DIR = "uploads";
    @Autowired
    private ResourceLoader resourceLoader;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String provideUploadInfo(Model model) throws IOException {
        model.addAttribute("files", Files.walk(Paths.get(ROOT_DIR))
                .filter(path -> !path.equals(Paths.get(ROOT_DIR)))
                .map(path -> Paths.get(ROOT_DIR).relativize(path))
                .map(path -> linkTo(methodOn(UploadController.class).getFile(path.toString())).withRel(path.toString()))
                .collect(Collectors.toList()));
        return "uploadForm";
    }

    //显示图片的方法关键 匹配路径像 localhost:8080/b7c76eb3-5a67-4d41-ae5c-1642af3f8746.png
    @RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {

        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT_DIR, filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        System.out.println(request.getParameter("member"));
        if (!file.isEmpty()) {
            try {
                Files.copy(file.getInputStream(), Paths.get(ROOT_DIR, file.getOriginalFilename()));
                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded " + file.getOriginalFilename() + "!");
            } catch (IOException | RuntimeException e) {
                redirectAttributes.addFlashAttribute("message", "Failued to upload " + file.getOriginalFilename() + " => " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " because it was empty");
        }

        return "redirect:/";
    }
}
