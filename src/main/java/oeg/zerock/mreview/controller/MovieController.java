package oeg.zerock.mreview.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import oeg.zerock.mreview.dto.MovieDTO;
import oeg.zerock.mreview.dto.PageRequestDTO;
import oeg.zerock.mreview.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class MovieController {
    private static final String UPLOAD_PATH = "C:\\upload";

    private final MovieService movieService;

    @GetMapping("/register")
    public void register(){
    }

    @PostMapping("/register")
    public String regster(MovieDTO movieDTO, RedirectAttributes redirectAttributes){
        log.info("movieDTO: "+movieDTO);

        Long mno = movieService.register(movieDTO);

        redirectAttributes.addFlashAttribute("msg", mno);

        return "redirect:/movie/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageResultDTO, Model model){
        log.info("pageRequestDTO"+ pageResultDTO);
        model.addAttribute("result", movieService.getList(pageResultDTO));


    }
    @GetMapping({"/read","/modify"})
    public void read(long mno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){
        log.info("mno: "+mno);
        MovieDTO movieDTO = movieService.getMovie(mno);
        model.addAttribute("dto", movieDTO);
    }

    @RequestMapping(value = "/fileupload",method = RequestMethod.POST)
    public void upload(MultipartFile uploadfile){
        log.info("upload() POST 호출");
        log.info("파일 이름: {}", uploadfile.getOriginalFilename());
        log.info("파일 크기: {}", uploadfile.getSize());

        saveFile(uploadfile);
        log.info("8");
    }

    private String saveFile(MultipartFile file){
        // 파일 이름 변경
        UUID uuid = UUID.randomUUID();
        String saveName = uuid + "_" + file.getOriginalFilename();

        log.info("saveName: {}",saveName);

        // 저장할 File 객체를 생성(껍데기 파일)ㄴ
        File saveFile = new File(UPLOAD_PATH,saveName); // 저장할 폴더 이름, 저장할 파일 이름
        log.info("1");
        try {
            log.info("2");
            file.transferTo(saveFile); // 업로드 파일에 saveFile이라는 껍데기 입힘
        } catch (IOException e) {
            log.info("4");
            e.printStackTrace();
            log.info("5");
            return null;
        }
        log.info("3");
        return null;

    } // end saveFile(

}
