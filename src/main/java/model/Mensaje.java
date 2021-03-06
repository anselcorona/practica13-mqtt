package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mensaje {

    private int idDispositivo;
    private Long temperatura;
    private Long humedad;
    private String fecha;

    public Mensaje(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD/MM/YYYY HH:mm:ss");
        Date date = new Date();
        setIdDispositivo((int) (Math.random() * 500) + 1);
        setFecha(simpleDateFormat.format(date));
        setHumedad((long) (Math.random() * 100) + 1);
        setTemperatura((long) (Math.random() * 50) + 1);
    }



    public int getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public Long getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Long temperatura) {
        this.temperatura = temperatura;
    }

    public Long getHumedad() {
        return humedad;
    }

    public void setHumedad(Long humedad) {
        this.humedad = humedad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
