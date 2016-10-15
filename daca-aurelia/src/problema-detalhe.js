import {inject} from 'aurelia-framework';
import {HttpClient} from 'aurelia-fetch-client';
import 'fetch';

@inject(HttpClient)
export class Detalhe {
  heading = 'Editar Problema';
  problema = {};

  constructor(http) {
    http.configure(config => {
      config
        .useStandardConfiguration()
        .withBaseUrl('http://localhost:8080/');
    });

    this.http = http;
  }

  resolver(){
  	console.log(this.problema.teste);
  }

  activate(params) {
     return this.http.fetch('problema/' + params.cod)
        .then(response => response.json())
         .then(problema => this.problema = problema);
   }
}