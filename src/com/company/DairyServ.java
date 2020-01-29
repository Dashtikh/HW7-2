package com.company;


import java.util.List;


public class DairyServ {
    private DairyServ () {
    }

    private static DairyServ carServ = new DairyServ ();

    public static DairyServ getInstance () {
        return carServ;
    }

    public void save (DairyEnti carEnti) throws Exception {
        try (DairyRepo carRepo = new DairyRepo ()) {
            carEnti.setprice (carEnti.getPrice () - (carEnti.getPrice () * 10 / 100));
            carRepo.insert (carEnti);
            carRepo.commit ();
        }
    }
    public void edit (DairyEnti carEnti) throws Exception{
        try (DairyRepo carRepo=new DairyRepo ()){
            carEnti.setprice (carEnti.getPrice ()-(carEnti.getPrice ()*10/100));
            carRepo.update (carEnti);
            carRepo.commit ();
        }
    }
    public void remove(int model) throws Exception{
        try (DairyRepo carRepo=new DairyRepo ()){
            carRepo.delete (model);
            carRepo.commit ();
        }
    }
    public List<DairyEnti> report() throws Exception{
        List<DairyEnti> carEntis;
        try (DairyRepo carRepo=new DairyRepo ()){
            carEntis=carRepo.select ();
        }
        return carEntis;
    }

}
