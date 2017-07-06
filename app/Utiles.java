package app;

public class Utiles {

    /**
     * Obtener Dia de La Semana
     *
     * @param diaSemana
     * @return String
     */
    static public String getDiaDeLaSemana (int diaSemana){
        String day = "";
        switch(diaSemana){
            case 1:
                day = "Domingo";
                break;
            case 2:
                day = "Lunes";
                break;
            case 3:
                day = "Martes";
                break;
            case 4:
                day = "Miércoles";
                break;
            case 5:
                day = "Jueves";
                break;
            case 6:
                day = "Viernes";
                break;
            case 7:
                day = "Sábado";
                break;
        }
        return day;
    }
    
}
