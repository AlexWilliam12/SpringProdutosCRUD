package com.projetoSMD.produtosCRUD.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {

	@RequestMapping("/produtos")
	public String produtosTemplate() {
		return "produtos";
	}
}
