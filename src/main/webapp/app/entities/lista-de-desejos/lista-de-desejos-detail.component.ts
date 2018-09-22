import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IListaDeDesejos } from 'app/shared/model/lista-de-desejos.model';

@Component({
    selector: 'jhi-lista-de-desejos-detail',
    templateUrl: './lista-de-desejos-detail.component.html'
})
export class ListaDeDesejosDetailComponent implements OnInit {
    listaDeDesejos: IListaDeDesejos;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ listaDeDesejos }) => {
            this.listaDeDesejos = listaDeDesejos;
        });
    }

    previousState() {
        window.history.back();
    }
}
