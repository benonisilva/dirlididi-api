import {inject} from 'aurelia-framework';
import {HttpClient} from 'aurelia-fetch-client';
import 'fetch';

@inject(HttpClient)
export class Problemas {
  heading = 'Problemas';
  problemas = [];

  constructor(http) {
    http.configure(config => {
      config
        .useStandardConfiguration()
        .withBaseUrl('http://localhost:8080/problema/');
    });

    this.http = http;
  }

  activate() {
    return this.http.fetch('sumario')
      .then(response => response.json())
      .then(problemas => this.problemas = problemas);
  }
}
