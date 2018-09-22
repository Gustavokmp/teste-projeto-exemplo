import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IListaDeDesejos } from 'app/shared/model/lista-de-desejos.model';
import { ListaDeDesejosService } from './lista-de-desejos.service';

@Component({
    selector: 'jhi-lista-de-desejos-delete-dialog',
    templateUrl: './lista-de-desejos-delete-dialog.component.html'
})
export class ListaDeDesejosDeleteDialogComponent {
    listaDeDesejos: IListaDeDesejos;

    constructor(
        private listaDeDesejosService: ListaDeDesejosService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.listaDeDesejosService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'listaDeDesejosListModification',
                content: 'Deleted an listaDeDesejos'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-lista-de-desejos-delete-popup',
    template: ''
})
export class ListaDeDesejosDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ listaDeDesejos }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ListaDeDesejosDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.listaDeDesejos = listaDeDesejos;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
