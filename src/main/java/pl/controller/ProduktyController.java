package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.model.Produkty;
import pl.model.Zdjecia;
import pl.repository.ProduktyRepository;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ProduktyController {


    private ProduktyRepository produktyRepository;
    CheckAuth user;

    @Autowired
    public void setProduktyRepository(ProduktyRepository produktyRepository) {
        this.produktyRepository = produktyRepository;
    }

    @GetMapping("/produkty")
    public String register(Model model) {
    	user = new CheckAuth(model);

        List<Produkty> all = produktyRepository.findAll();
        model.addAttribute("all", all);

        return user.checkPracownik("produkty");
    }


    @GetMapping("/addProduct")
    public String addProduct(Model model) {

        model.addAttribute("product",new Produkty());
        return user.checkPracownik("addProduct");
    }

    @PostMapping("/addProduct")
    public String addProducts(@ModelAttribute Produkty produkty,Model model,@RequestParam(value = "plik[]",required = false) MultipartFile[] file){
    	user = new CheckAuth(model);
        int size ;

        System.out.println(produkty);
        if(file==null) {
            size=0;
        }
        else {
            size=file.length;
            
            if(file[0] != null) {

                if (file.length > 9) {
                    model.addAttribute("limit", "Limit zdjec to 9");
                    return "addForm";
                }

                for (int i = 0; i < file.length; i++) {
                    String images = file[i].getContentType();

                    images = images.substring(0, images.indexOf('/'));

                    if (images.equals("image")) {
                    } else if (images.equals("application")) {
                        size--;
                    } else {
                        model.addAttribute("badExtend", "Moga byc tylko zdjecia");
                        return "addForm";
                    }
                }
            }




        }






        if (size >= 1 ) {
            for (int i = 0; i < file.length; i++) {
                try {
                    String extend = file[i].getOriginalFilename();
                    extend = extend.substring(extend.indexOf('.'));

                    UUID uuid = UUID.randomUUID();
                    String filename = "src\\main\\resources\\static\\images\\products\\" + uuid.toString() + extend;
                    byte[] bytes = file[i].getBytes();
                    File files = new File(filename);

                    files.createNewFile();
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(files));
                    bufferedOutputStream.write(bytes);
                    bufferedOutputStream.close();
                    Zdjecia zdjecia = new Zdjecia();
                    zdjecia.setAdres("images/products/" + uuid.toString() + extend);
                    produkty.getZdjecia().add(zdjecia);



                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        else
        {
            produkty.getZdjecia().add(null);
        }



        produktyRepository.save(produkty);
        List<Produkty> all = produktyRepository.findAll();
        model.addAttribute("all", all);
        model.addAttribute("succes","Operacja przebiegła pomyślnie");
        return "produkty";

    }

    
    
    @GetMapping("/Usun") 
    public String usun(Model model,@RequestParam Long ID) {
    	produktyRepository.deleteById(ID);
    	   List<Produkty> all = produktyRepository.findAll();
           model.addAttribute("all", all);
           model.addAttribute("succes","Usunięto produkt");
    	return "produkty";
    }
    
    
    @GetMapping("/Edytuj")
    public String edytuj(Model model,@RequestParam Long ID) {
    	
    	Optional<Produkty> produkty=produktyRepository.findById(ID);
    	Produkty p=produkty.get();
    	model.addAttribute("product",p);
    	return "edycjaProduktu";
    }

}
