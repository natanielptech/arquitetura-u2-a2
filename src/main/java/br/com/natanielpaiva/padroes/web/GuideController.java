package br.com.natanielpaiva.padroes.web;

import br.com.natanielpaiva.padroes.catalogo.PatternCatalog;
import br.com.natanielpaiva.padroes.catalogo.PatternInfo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class GuideController {
    private final PatternCatalog catalog;

    public GuideController(PatternCatalog catalog) {
        this.catalog = catalog;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("patterns", catalog.all());
        model.addAttribute("criacionais", catalog.byCategory("Criacional"));
        model.addAttribute("estruturais", catalog.byCategory("Estrutural"));
        model.addAttribute("comportamentais", catalog.byCategory("Comportamental"));
        return "index";
    }

    @GetMapping("/catalogo/{slug}")
    public String pattern(@PathVariable String slug, Model model) {
        PatternInfo pattern = catalog.find(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("pattern", pattern);
        return "pattern";
    }
}
