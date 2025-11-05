package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador Web MVC para gestionar vistas con Thymeleaf.
 * Permite navegar entre las páginas de notas, carpetas, etiquetas, etc.
 * 
 * @author 
 * @version 1.0
 */
@Controller
@RequestMapping("/web")
public class WebViewController {

    @Autowired private NotaRepository notaRepository;
    @Autowired private CarpetaRepository carpetaRepository;
    @Autowired private EtiquetaRepository etiquetaRepository;
    @Autowired private MultimediaRepository multimediaRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    /**
     * Página principal (home del sistema)
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Inicio - Gestor de Notas");
        model.addAttribute("mensaje", "Bienvenido a tu espacio de organización digital 🌟");
        return "index";
    }

    // Notas
    @GetMapping("/notas")
    public String listarNotas(Model model) {
        List<Nota> notas = notaRepository.findAll();
        model.addAttribute("notas", notas);
        model.addAttribute("pageTitle", "Listado de Notas");
        return "notas/list";
    }

    @GetMapping("/notas/{id}")
    public String detalleNota(@PathVariable Integer id, Model model) {
        Nota nota = notaRepository.findById(id).orElse(null);
        if (nota == null) return "redirect:/web/notas";
        model.addAttribute("nota", nota);
        model.addAttribute("pageTitle", "Detalle de Nota");
        return "notas/detail";
    }

    @GetMapping("/notas/nueva")
    public String nuevaNota(Model model) {
        List<Etiqueta> etiquetas = etiquetaRepository.findAll();
        List<Carpeta> carpetas = carpetaRepository.findAll();
        model.addAttribute("nota", new Nota());
        model.addAttribute("etiquetas", etiquetas);
        model.addAttribute("carpetas", carpetas);
        model.addAttribute("pageTitle", "Crear Nueva Nota");
        return "notas/form";
    }

    @GetMapping("/notas/{id}/editar")
    public String editarNota(@PathVariable Integer id, Model model) {
        Nota nota = notaRepository.findById(id).orElse(null);
        if (nota == null) return "redirect:/web/notas";
        List<Etiqueta> etiquetas = etiquetaRepository.findAll();
        model.addAttribute("nota", nota);
        model.addAttribute("etiquetas", etiquetas);
        model.addAttribute("pageTitle", "Editar Nota");
        return "notas/form";
    }

    // Carpetas
    @GetMapping("/carpetas")
    public String listarCarpetas(Model model) {
        List<Carpeta> carpetas = carpetaRepository.findAll();
        model.addAttribute("carpetas", carpetas);
        model.addAttribute("pageTitle", "Carpetas de Usuario");
        return "carpetas/list";
    }

    @GetMapping("/carpetas/{id}")
    public String detalleCarpeta(@PathVariable Integer id, Model model) {
        Carpeta carpeta = carpetaRepository.findById(id).orElse(null);
        if (carpeta == null) return "redirect:/web/carpetas";
        model.addAttribute("carpeta", carpeta);
        model.addAttribute("pageTitle", "Detalle de Carpeta");
        return "carpetas/detail";
    }

    @GetMapping("/carpetas/nueva")
    public String nuevaCarpeta(Model model) {
        model.addAttribute("carpeta", new Carpeta());
        model.addAttribute("pageTitle", "Crear Nueva Carpeta");
        return "carpetas/form";
    }

    @GetMapping("/carpetas/{id}/editar")
    public String editarCarpeta(@PathVariable Integer id, Model model) {
        Carpeta carpeta = carpetaRepository.findById(id).orElse(null);
        if (carpeta == null) return "redirect:/web/carpetas";
        model.addAttribute("carpeta", carpeta);
        model.addAttribute("pageTitle", "Editar Carpeta");
        return "carpetas/form";
    }

    // Etiquetas
    public String listarEtiquetas(Model model) {
        List<Etiqueta> etiquetas = etiquetaRepository.findAll();
        model.addAttribute("etiquetas", etiquetas);
        model.addAttribute("pageTitle", "Gestión de Etiquetas");
        return "etiquetas/list";
    }

    @GetMapping("/etiquetas/nueva")
    public String nuevaEtiqueta(Model model) {
        model.addAttribute("etiqueta", new Etiqueta());
        model.addAttribute("pageTitle", "Crear Nueva Etiqueta");
        return "etiquetas/form";
    }

    @GetMapping("/etiquetas/{id}/editar")
    public String editarEtiqueta(@PathVariable Integer id, Model model) {
        Etiqueta etiqueta = etiquetaRepository.findById(id).orElse(null);
        if (etiqueta == null) return "redirect:/web/etiquetas";
        model.addAttribute("etiqueta", etiqueta);
        model.addAttribute("pageTitle", "Editar Etiqueta");
        return "etiquetas/form";
    }

    // Multimedia
    @GetMapping("/multimedia")
    public String listarMultimedia(Model model) {
        List<Multimedia> archivos = multimediaRepository.findAll();
        model.addAttribute("archivos", archivos);
        model.addAttribute("pageTitle", "Archivos Multimedia");
        return "multimedia/list";
    }

    @GetMapping("/multimedia/{id}")
    public String detalleMultimedia(@PathVariable Integer id, Model model) {
        Multimedia archivo = multimediaRepository.findById(id).orElse(null);
        if (archivo == null) return "redirect:/web/multimedia";
        model.addAttribute("archivo", archivo);
        model.addAttribute("pageTitle", "Detalle del Archivo");
        return "multimedia/detail";
    }

    // Usuarios
    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("pageTitle", "Usuarios Registrados");
        return "usuarios/list";
    }

    @GetMapping("/usuarios/{id}")
    public String detalleUsuario(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) return "redirect:/web/usuarios";
        model.addAttribute("usuario", usuario);
        model.addAttribute("pageTitle", "Perfil de Usuario");
        return "usuarios/detail";
    }
}
