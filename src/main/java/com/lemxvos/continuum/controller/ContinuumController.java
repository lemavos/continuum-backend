package com.lemxvos.continuum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContinuumController {

    @GetMapping("/")
    public String index() {
        return """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <title>Continuum</title>
        </head>
        <body>
            <h1>Continuum rodando</h1>

            <button onclick="criar()">Criar</button>

            <script>
                function criar() {
                    fetch('/metas', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({ nome: 'Teste' })
                    })
                    .then(r => r.text())
                    .then(console.log)
                    .catch(console.error);
                }
            </script>
        </body>
        </html>
        """;
    }
}
