package org.example.aivaje2.MrBean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.example.aivaje2.DAO.PolnilnicaDAO;
import org.example.aivaje2.DAO.PonudnikDAO;
import org.example.aivaje2.DAO.UserDAO;
import org.example.aivaje2.Service.PolnilnicaService;
import org.example.aivaje2.Service.PonudnikService;
import org.example.aivaje2.Service.UserService;
import org.example.aivaje2.VAO.Polnilnica;
import org.example.aivaje2.VAO.Ponudnik;
import org.example.aivaje2.VAO.User;

import java.io.Serializable;

@Data
@Named("vstaviBean")
@ViewScoped
public class VstaviPolnilnicoBean implements Serializable {

    private String ime;
    private String email;
    private double balance;

    private String carType;

    private int postajaID;
    private String lokacija;
    private String naziv;
    private int hitrost;
    private boolean isActive;
    private String regija;

    private int ponudnikID;

    private PonudnikDAO ponudnikDAO = PonudnikDAO.getInstance();
    private PolnilnicaDAO polnilnicaDAO = PolnilnicaDAO.getInstance();
    private UserDAO userDAO = UserDAO.getInstance();

    PonudnikService ponudnikService = new PonudnikService();
    PolnilnicaService polnilnicaService = new PolnilnicaService();
    UserService userService = new UserService();

    @PostConstruct
    public void init(){
        if(ponudnikDAO.vrniVsePonudnike().isEmpty()){

            ponudnikService.dodajPonudnika(1,"Tesla");
            ponudnikService.dodajPonudnika(2,"BMW");
            ponudnikService.dodajPonudnika(3,"Mercedes");
            ponudnikService.dodajPonudnika(4,"Audi");
            ponudnikService.dodajPonudnika(5,"Ferrari");

            polnilnicaService.dodajPostajo(1,"Maribor","MB Tesla", ponudnikService.pridobiPonudnika(1), true,50,"Stajerska");
            polnilnicaService.dodajPostajo(2, "Ljubljana", "LJ BMW", ponudnikService.pridobiPonudnika(2), true, 60, "Center");
            polnilnicaService.dodajPostajo(3, "Celje", "Celje Mercedes", ponudnikService.pridobiPonudnika(3), true, 70, "Vzhodna");
            polnilnicaService.dodajPostajo(4, "Koper", "KP Audi", ponudnikService.pridobiPonudnika(4), false, 80, "Jadranska");
            polnilnicaService.dodajPostajo(5, "Nova Gorica", "NG Ferrari", ponudnikService.pridobiPonudnika(5), true, 90, "Zahodna");
            polnilnicaService.dodajPostajo(6, "Murska Sobota", "MS Tesla", ponudnikService.pridobiPonudnika(1), true, 100, "Severna");
            polnilnicaService.dodajPostajo(7, "Velenje", "Velenje BMW", ponudnikService.pridobiPonudnika(2), false, 110, "Osrednja");
            polnilnicaService.dodajPostajo(8, "Ptuj", "Ptuj Mercedes", ponudnikService.pridobiPonudnika(3), true, 120, "Vzhod");
            polnilnicaService.dodajPostajo(9, "Novo mesto", "NM Audi", ponudnikService.pridobiPonudnika(4), true, 130, "Jugovzhodna");
            polnilnicaService.dodajPostajo(10, "Kranj", "Kranj Ferrari", ponudnikService.pridobiPonudnika(5), true, 140, "Severozahodna");

            int postajaID = polnilnicaService.vrniVsePostaje().size();
            if(postajaID <= 0){
                throw new IllegalArgumentException("Napaka pri dodajanju polnilnice");
            }
            System.out.println("DID THE DEED");
        }
    }

    public void addPolnilnica(){
        polnilnicaService.dodajPostajo(postajaID,lokacija,naziv,ponudnikService.pridobiPonudnika(ponudnikID),isActive,hitrost,regija);
    }

    public void addUser(){
        userService.dodajUser(new User(ime,email,balance,carType));
    }

    public void printData(){
        System.out.println("Postaje:");
        for(Polnilnica p : polnilnicaService.vrniVsePostaje()){
            System.out.println(p.getNaziv());
        }
        System.out.println("Ponudniki:");
        for(Ponudnik p : ponudnikService.vrniVsePonudnike()){
            System.out.println(p.getNaziv());
        }
    }
}
