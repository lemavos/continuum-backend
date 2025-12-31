package com.lemxvos.continuum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContinuumController {

    @GetMapping("/")
    public String index() {
        return """
               <!DOCTYPE html>
               <html lang="pt-BR">
               <head>
                 <meta charset="UTF-8" />
                 <title>Continuum – Teste de Metas</title>
                 <style>
                   body {
                     font-family: Arial, sans-serif;
                     padding: 20px;
                     background: #0f172a;
                     color: #e5e7eb;
                   }
               
                   button {
                     padding: 8px 12px;
                     cursor: pointer;
                     border: none;
                     border-radius: 6px;
                     background: #2563eb;
                     color: white;
                     margin-top: 10px;
                   }
               
                   input {
                     padding: 6px;
                     margin: 4px;
                   }
               
                   .grid {
                     display: grid;
                     grid-template-columns: repeat(auto-fill, minmax(90px, 1fr));
                     gap: 10px;
                     margin-top: 20px;
                   }
               
                   .day {
                     padding: 10px;
                     border-radius: 8px;
                     text-align: center;
                     cursor: pointer;
                     background: #1e293b;
                     border: 2px solid #334155;
                   }
               
                   .done {
                     background: #16a34a;
                     border-color: #22c55e;
                   }
                 </style>
               </head>
               <body>
               
               <h1>Continuum – Quadro de Metas</h1>
               
               <h3>Criar Meta</h3>
               <input id="titulo" placeholder="Título" />
               <input id="inicio" type="date" />
               <input id="fim" type="date" />
               <br />
               <button onclick="criarMeta()">Criar</button>
               
               <h3>Meta</h3>
               <div id="meta"></div>
               <div class="grid" id="grid"></div>
               
               <script>
                 const API = "http://localhost:8080/metas";
                 let metaId = null;
               
                 async function criarMeta() {
                   const body = {
                     titulo: titulo.value,
                     dataInicio: inicio.value,
                     dataFim: fim.value
                   };
               
                   const res = await fetch(API, {
                     method: "POST",
                     headers: { "Content-Type": "application/json" },
                     body: JSON.stringify(body)
                   });
               
                   const data = await res.json();
                   metaId = data.id;
                   render(data);
                 }
               
                 async function carregarMeta() {
                   const res = await fetch(`${API}/${metaId}`);
                   const data = await res.json();
                   render(data);
                 }
               
                 async function toggleDia(dataDia, atual) {
                   await fetch(`${API}/${metaId}/dias/${dataDia}`, {
                     method: "PATCH",
                     headers: { "Content-Type": "application/json" },
                     body: JSON.stringify({ concluido: !atual })
                   });
               
                   carregarMeta();
                 }
               
                 function render(meta) {
                   document.getElementById("meta").innerText = meta.titulo;
               
                   const grid = document.getElementById("grid");
                   grid.innerHTML = "";
               
                   meta.dias.forEach(dia => {
                     const div = document.createElement("div");
                     div.className = "day" + (dia.concluido ? " done" : "");
                     div.innerText = dia.data;
                     div.onclick = () => toggleDia(dia.data, dia.concluido);
                     grid.appendChild(div);
                   });
                 }
               </script>
               
               </body>
               </html>
        """;
    }
}
