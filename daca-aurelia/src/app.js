export class App {
  configureRouter(config, router) {
    config.title = 'Dirlididi';
    config.map([
      { route: ['', 'problemas'], name: 'problemas',      moduleId: 'problemas',      nav: true, title: 'Problemas' },
      { route: 'users',         name: 'users',        moduleId: 'users',        nav: true, title: 'IDE' },
      {
        route: 'problema-detalhe/:cod',
        name: 'problema-detalhe',
        moduleId: 'problema-detalhe',
        nav: false,
        title: 'Problema'
       }
    ]);

    this.router = router;
  }
}
