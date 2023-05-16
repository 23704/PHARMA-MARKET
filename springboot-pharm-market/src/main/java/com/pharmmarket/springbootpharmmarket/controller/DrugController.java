package com.pharmmarket.springbootpharmmarket.controller;

import com.pharmmarket.springbootpharmmarket.model.Drug;
import com.pharmmarket.springbootpharmmarket.model.PharmacyDirector;
import com.pharmmarket.springbootpharmmarket.service.DrugService;
import com.pharmmarket.springbootpharmmarket.service.PharmacyDirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/drugs")
public class DrugController {

    private Logger logger = Logger.getLogger(getClass().getName());
    private DrugService drugService;

    private PharmacyDirectorService pharmacyDirectorService;
    @Autowired
    public DrugController(DrugService drugService, PharmacyDirectorService pharmacyDirectorService) {
        this.drugService = drugService;
        this.pharmacyDirectorService = pharmacyDirectorService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder)
    {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    private static String UPLOAD_DIR = "./drug_images/";

    @GetMapping("/getAddForm")
    public String getAddForm(Model theModel) {

        Drug drug = new Drug();
       PharmacyDirector thePharma = new PharmacyDirector();
        thePharma.setId(2L);
        theModel.addAttribute("drug", drug);


       theModel.addAttribute("AuthId", thePharma.getId());
//        theModel.addAttribute("theUser", theUser);
        return "registration";

    }

    @PostMapping("/add")
    public String addDrug(@RequestParam("picture") MultipartFile pic, @ModelAttribute("drug") Drug theDrug,
                          BindingResult theBindingResult,
                          HttpSession session, HttpServletRequest request, PharmacyDirector pharmacyDirector) throws IOException, ParseException {
//        logger.warning("==============Author==============");
//        Long pharmaId = null;
//
//        if (StringUtils.hasText(AuthId)) {
//            try {
//                pharmaId = Long.parseLong(AuthId);
//            } catch (NumberFormatException e) {
//                // Handle the case when AuthId is not a valid number
//                System.out.println("the Auth Id is not valid");
//                // Display an error message or take appropriate action
//                return "redirect:/drugs/getAddForm";
//            }
//        } else {
//            System.out.println("the Auth id is empty");
//            // Display an error message or take appropriate action
//            return "redirect:/drugs/getAddForm";
//        }

//        PharmacyDirector existingPharma = pharmacyDirectorService.findById(pharmaId);
//        logger.warning("\n " + existingPharma.toString() + "\n");

        // now Add the userId to the session of future reference in the session
//        session = request.getSession();
//        if (session.getAttribute("AuthId") == null) {
//            session.setAttribute("AuthId", pharmaId);
//            logger.warning("================AuthId set as session attribute============");
//        }

        // Normalize file name
            String Image = StringUtils.cleanPath(pic.getOriginalFilename());
            Drug dbDrug = new Drug(theDrug.getDrugName(),theDrug.getDescription(),theDrug.getPrice(), theDrug.getFabDate(),
                    theDrug.getExpDate(),theDrug.getQuantity(), theDrug.getCategory()
                   );
//            if (theDrug.getId() == 0) {
//                dbDrug = new Drug();
//                logger.info(">>>>>>>>>>>>>>>>>>>>>> New Drug instance");
//            } else {
//                dbDrug = drugService.findById(theDrug.getId());
//                logger.info(">>>>>>>>>>>>>>>>>>>>>>Drug to update " + dbDrug);
//            }
//        dbDrug.setFullNames(theStudent.getFullNames());
//            dbDrug.setDrugName(theDrug.getDrugName());
//            dbDrug.setDescription(theDrug.getDescription());
//            dbDrug.setCategory(theDrug.getCategory());
//            dbDrug.setPrice(theDrug.getPrice());
//            dbDrug.setQuantity(theDrug.getQuantity());
//            dbDrug.setFabDate(theDrug.getFabDate());
//            dbDrug.setExpDate(theDrug.getExpDate());

            // Save image to server No Duplicate
            try {
                byte[] bytes = pic.getBytes();
                String originalPicName = pic.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + originalPicName);
                if (Files.notExists(filePath)) {
                    Files.write(filePath, bytes);
                    dbDrug.setDrugImage(originalPicName);
                    logger.info(">>>>>>>>>>>>>>>>>>>>>Path for Image" + filePath);
                } else {
                    dbDrug.setDrugImage(originalPicName);
                    logger.info(">>>>>>>>>>>>>>>>>>>>>Image already exists: " + filePath);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // save the employee
            drugService.save(dbDrug);
            //mailService.sendMail("studentdody330@gmail.com");
            return "redirect:/drugs/list";


    }

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        String key = null;

        return listByPage(theModel, 1, key);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model theModel, @PathVariable("pageNumber") int currentPage,
                             @Param("key") String key) {


        //pagination
        Page<Drug> page = drugService.findAll(key, currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        // get drugs from DB
        List<Drug> theDrugs = page.getContent();
//        //get user
//        List<Integer> users = userService.findAllIds();
        //add to the spring model
//        theModel.addAttribute("users", users);
        theModel.addAttribute("currentPage", currentPage);
        theModel.addAttribute("totalItems", totalItems);
        theModel.addAttribute("totalPages", totalPages);
        theModel.addAttribute("theDrugs", theDrugs);
        theModel.addAttribute("key", key);

        return "drug-listing";

    }

    @GetMapping("/view/{filename:.+}")
    public void viewFile(@PathVariable String filename, HttpServletResponse response) throws IOException {
        Path file = Paths.get(UPLOAD_DIR + filename);
        logger.info(">>>>>>>>>>>>>>>>>>>>>>Name of file at download " + filename);
        if (Files.exists(file)) {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=" + filename);
            Files.copy(file, response.getOutputStream());
            response.getOutputStream().flush();
        }
    }

    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = new FileSystemResource(UPLOAD_DIR + filename);
        if (file.exists() && file.isReadable()) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE).body(file);
        } else {
            return ResponseEntity.notFound().build();
        }


    }
    @PostMapping("/showFormForUpdate")
    public String updateDrug(@RequestParam("drugId") Long theId, Model theModel) {

        //get the job from service
        Drug theDrug = drugService.findById(theId);
        //set job in the model to prepolulate the form
        theModel.addAttribute("drug", theDrug);
        //send over to our form
        return "registration";
    }
    @PostMapping("/deleteDrug")
    public String deleteDrug(@RequestParam("drugId") Long theId){

        drugService.deleteById(theId);
        //redirect to the list
        return "redirect:/drugs/list";
    }

}
