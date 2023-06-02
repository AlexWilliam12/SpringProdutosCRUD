package com.projetoSMD.produtosCRUD.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

	@GetMapping("/produtos")
	public String produtosTemplate() {
		return "produtos";
	}
}
