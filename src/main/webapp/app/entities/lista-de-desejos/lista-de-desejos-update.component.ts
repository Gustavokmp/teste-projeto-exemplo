import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IListaDeDesejos } from 'app/shared/model/lista-de-desejos.model';
import { ListaDeDesejosService } from './lista-de-desejos.service';
import { ICliente } from 'app/shared/model/cliente.model';
import { ClienteService } from 'app/entities/cliente';

@Component({
    selector: 'jhi-lista-de-desejos-update',
    templateUrl: './lista-de-desejos-update.component.html'
})
export class ListaDeDesejosUpdateComponent implements OnInit {
    private _listaDeDesejos: IListaDeDesejos;
    isSaving: boolean;

    clientes: ICliente[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private listaDeDesejosService: ListaDeDesejosService,
        private clienteService: ClienteService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ listaDeDesejos }) => {
            this.listaDeDesejos = listaDeDesejos;
        });
        this.clienteService.query().subscribe(
            (res: HttpResponse<ICliente[]>) => {
                this.clientes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.listaDeDesejos.id !== undefined) {
            this.subscribeToSaveResponse(this.listaDeDesejosService.update(this.listaDeDesejos));
        } else {
            this.subscribeToSaveResponse(this.listaDeDesejosService.create(this.listaDeDesejos));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IListaDeDesejos>>) {
        result.subscribe((res: HttpResponse<IListaDeDesejos>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackClienteById(index: number, item: ICliente) {
        return item.id;
    }
    get listaDeDesejos() {
        return this._listaDeDesejos;
    }

    set listaDeDesejos(listaDeDesejos: IListaDeDesejos) {
        this._listaDeDesejos = listaDeDesejos;
    }
}
