package com.projeto.teste.helper;

public class DateHelper {
    public static String getMesAnoByData(String data){
        String retornoData[] = data.split("/");

        String mes = retornoData[1];
        String ano = retornoData[2];
        String mesAno = mes + ano;

        return mesAno;
    }

    public static int getDiaByData(String data){
        String retornoData[] = data.split("/");
        String dia = retornoData[0];

        return Integer.parseInt(dia);
    }

    public static int getMesByData(String data){
        String retornoData[] = data.split("/");
        String mes = retornoData[1];

        return Integer.parseInt(mes);
    }

    public static int getAnoByData(String data){
        String retornoData[] = data.split("/");
        String ano = retornoData[2];

        return Integer.parseInt(ano);
    }

    public static int getMinutos(String data){
        String retornoHora[] = data.split(":");
        String minuto = retornoHora[1];

        return Integer.parseInt(minuto);
    }

    public static int getHora(String data){
        String retornoHora[] = data.split(":");
        String hora = retornoHora[0];

        return Integer.parseInt(hora);
    }
}
