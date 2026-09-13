package com.gabriel.bate_ponto.Service;

import com.gabriel.bate_ponto.Service.usuarios.UsuarioService;
import com.gabriel.bate_ponto.dto.pesquisa.PesquisaRegistroPontoDTO;
import com.gabriel.bate_ponto.dto.registroPonto.RegistroPontoResponse;
import com.gabriel.bate_ponto.dto.usuario.UsuarioResponseDTO;
import com.gabriel.bate_ponto.exceptions.exceptions.CargoNaoEncontradaException;
import com.gabriel.bate_ponto.exceptions.exceptions.PontoJaRegistradoException;
import com.gabriel.bate_ponto.model.RegistroPonto;
import com.gabriel.bate_ponto.model.Usuario;
import com.gabriel.bate_ponto.repository.RegistroPontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class RegistroPontoService {

    @Autowired
    private RegistroPontoRepository pontoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public RegistroPontoResponse registrarPonto(){

        LocalDate data = LocalDate.now();
        LocalTime hora = LocalTime.now();
        Usuario usuario = usuarioService.retornarUsuarioAutenticado();

        RegistroPonto ponto = new RegistroPonto();
        ponto.setData(data);
        ponto.setHora(hora);
        ponto.setOrigem("Web");
        ponto.setTipo(retornarTipo(usuario,data));
        ponto.setUsuario(usuario);
        pontoRepository.save(ponto);
        return new RegistroPontoResponse(
                ponto.getData(),
                ponto.getHora(),
                ponto.getTipo());
    }
    public List<RegistroPontoResponse> listarPontoPorDiaDeHoje(){
        return pontoRepository.findByUsuarioAndData(usuarioService.retornarUsuarioAutenticado(),LocalDate.now())
                .orElseThrow(CargoNaoEncontradaException::new)
                .stream()
                .map(ponto -> new RegistroPontoResponse(ponto.getData(),ponto.getHora(),ponto.getTipo()))
                .toList();
    }

    public List<RegistroPontoResponse> listarPontoPorDiaDaPesquisa(PesquisaRegistroPontoDTO dto){
        return pontoRepository.findByUsuarioAndData(usuarioService.retornarUsuarioAutenticado(),dto.data())
                .orElseThrow(CargoNaoEncontradaException::new)
                .stream()
                .map(ponto -> new RegistroPontoResponse(ponto.getData(),ponto.getHora(),ponto.getTipo()))
                .toList();
    }

    public void validarSeExistePonto(Usuario usuario, LocalDate data){
        if(pontoRepository.existsByDataAndUsuarioAndTipo(data,usuario, "Saída")){
            throw new PontoJaRegistradoException();
        }
    }

    public String retornarTipo(Usuario usuario, LocalDate data){
        this.validarSeExistePonto(usuario,data);
        RegistroPonto ponto = pontoRepository.findTopByUsuarioAndDataOrderByHoraDesc(usuario,data);
        if (ponto == null){
            return "Início";
        } else if (ponto.getTipo().equals("Início")) {
            return "Intervalo";
        } else if (ponto.getTipo().equals("Intervalo")) {
            return "Fim Intervalo";
        }
        return "Saída";

    }











}

