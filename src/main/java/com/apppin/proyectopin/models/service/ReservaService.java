package com.apppin.proyectopin.models.service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.apppin.proyectopin.models.entity.Empresa;
import com.apppin.proyectopin.models.entity.Reserva;
import com.apppin.proyectopin.models.repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService implements IReserva {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private IEmpresa empresaService;

    @Override
    public List<Reserva> listarTodos() {
        return (List<Reserva>) reservaRepository.findAll();
    }

    @Override
    public void guardar(Reserva reserva) {
        reservaRepository.save(reserva);
    }

    @Override
    public Reserva buscarPorId(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public List<Reserva> listarReservasEmpresa(Long idEmpresa) {
        Empresa empresa = empresaService.buscarPorId(idEmpresa);
        List<Reserva> lista = listarTodos();
        List<Reserva> listaFinal = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEmpresa().equals(empresa)) {
                listaFinal.add(lista.get(i));
            }
        }
        return listaFinal;
    }

    @Override
    public List<Reserva> reservasOrdenadas(List<Reserva> listaReservas) {
        if (listaReservas.size() != 0) {
            quicksortDias(listaReservas, 0, listaReservas.size() - 1);
        }

        return listaReservas;
    }

    public static void quicksortDias(List<Reserva> A, int izq, int der) {

        Reserva reservaPivote = A.get(izq); // tomamos primer elemento como pivote
        // LocalDate pivote = LocalDate.parse(reservaPivote.getFecha());
        LocalTime pivote = LocalTime.parse(reservaPivote.getHora(), DateTimeFormatter.ofPattern("HH:mm:ss"));
        int i = izq; // i realiza la búsqueda de izquierda a derecha
        int j = der; // j realiza la búsqueda de derecha a izquierda
        Reserva aux;

        while (i < j) { // mientras no se crucen las búsquedas
            while ((((LocalTime.parse(A.get(i).getHora(), DateTimeFormatter.ofPattern("HH:mm:ss"))).isBefore(pivote)
                    || ((LocalTime.parse(A.get(i).getHora(), DateTimeFormatter.ofPattern("HH:mm:ss"))).equals(pivote)))
                    && i < j))
                i++; // busca elemento mayor que pivote
            while (LocalTime.parse(A.get(j).getHora(), DateTimeFormatter.ofPattern("HH:mm:ss")).isAfter(pivote))
                j--; // busca elemento menor que pivote
            if (i < j) { // si no se han cruzado
                aux = A.get(i); // los intercambia
                Reserva aux1 = A.get(j);
                A.remove(i);
                A.add(i, aux1);
                A.remove(j);
                A.add(j, aux);
            }
        }

        Reserva aux3 = A.get(j);
        A.remove(izq);
        A.add(izq, aux3);
        A.remove(j);
        A.add(j, reservaPivote);

        if (izq < j - 1)
            quicksortDias(A, izq, j - 1); // ordenamos subarray izquierdo
        if (j + 1 < der)
            quicksortDias(A, j + 1, der); // ordenamos subarray derecho

    }

    @Override
    public List<Reserva> reservasHoy() {
        List<Reserva> listaReservas = listarTodos();
        List<Reserva> listaDefinitiva = new ArrayList<>();
        for (int i = 0; i < listaReservas.size(); i++) {
            LocalDate fecha = LocalDate.parse(listaReservas.get(i).getFecha());
            if (fecha.isEqual(LocalDate.now())) {
                listaDefinitiva.add(listaReservas.get(i));
            }
        }
        return listaDefinitiva;
    }

    @Override
    public Long obtenerIdUltimaReservaHecha() {
        List<Reserva> listaReservas = listarTodos();
        Long ultimaReserva = Long.parseLong("0");
        for (Reserva res : listaReservas) {
            if (res.getId() > ultimaReserva) {
                ultimaReserva = res.getId();
            }
        }
        return ultimaReserva;
    }


}
