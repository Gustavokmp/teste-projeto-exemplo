import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterCategoriaModule } from './categoria/categoria.module';
import { JhipsterProdutoModule } from './produto/produto.module';
import { JhipsterClienteModule } from './cliente/cliente.module';
import { JhipsterEnderecoModule } from './endereco/endereco.module';
import { JhipsterListaDeDesejosModule } from './lista-de-desejos/lista-de-desejos.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        JhipsterCategoriaModule,
        JhipsterProdutoModule,
        JhipsterClienteModule,
        JhipsterEnderecoModule,
        JhipsterListaDeDesejosModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEntityModule {}
