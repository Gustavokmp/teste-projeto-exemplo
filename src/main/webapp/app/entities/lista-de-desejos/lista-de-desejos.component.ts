import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IListaDeDesejos } from 'app/shared/model/lista-de-desejos.model';
import { Principal } from 'app/core';
import { ListaDeDesejosService } from './lista-de-desejos.service';

@Component({
    selector: 'jhi-lista-de-desejos',
    templateUrl: './lista-de-desejos.component.html'
})
export class ListaDeDesejosComponent implements OnInit, OnDestroy {
    listaDeDesejos: IListaDeDesejos[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private listaDeDesejosService: ListaDeDesejosService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.listaDeDesejosService.query().subscribe(
            (res: HttpResponse<IListaDeDesejos[]>) => {
                this.listaDeDesejos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInListaDeDesejos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IListaDeDesejos) {
        return item.id;
    }

    registerChangeInListaDeDesejos() {
        this.eventSubscriber = this.eventManager.subscribe('listaDeDesejosListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
