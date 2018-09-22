import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSharedModule } from 'app/shared';
import {
    ListaDeDesejosComponent,
    ListaDeDesejosDetailComponent,
    ListaDeDesejosUpdateComponent,
    ListaDeDesejosDeletePopupComponent,
    ListaDeDesejosDeleteDialogComponent,
    listaDeDesejosRoute,
    listaDeDesejosPopupRoute
} from './';

const ENTITY_STATES = [...listaDeDesejosRoute, ...listaDeDesejosPopupRoute];

@NgModule({
    imports: [JhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ListaDeDesejosComponent,
        ListaDeDesejosDetailComponent,
        ListaDeDesejosUpdateComponent,
        ListaDeDesejosDeleteDialogComponent,
        ListaDeDesejosDeletePopupComponent
    ],
    entryComponents: [
        ListaDeDesejosComponent,
        ListaDeDesejosUpdateComponent,
        ListaDeDesejosDeleteDialogComponent,
        ListaDeDesejosDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterListaDeDesejosModule {}
