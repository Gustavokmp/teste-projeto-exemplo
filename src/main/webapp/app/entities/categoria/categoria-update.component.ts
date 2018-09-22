import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICategoria } from 'app/shared/model/categoria.model';
import { CategoriaService } from './categoria.service';
import { IProduto } from 'app/shared/model/produto.model';
import { ProdutoService } from 'app/entities/produto';

@Component({
    selector: 'jhi-categoria-update',
    templateUrl: './categoria-update.component.html'
})
export class CategoriaUpdateComponent implements OnInit {
    private _categoria: ICategoria;
    isSaving: boolean;

    categorias: ICategoria[];

    produtos: IProduto[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private categoriaService: CategoriaService,
        private produtoService: ProdutoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ categoria }) => {
            this.categoria = categoria;
        });
        this.categoriaService.query().subscribe(
            (res: HttpResponse<ICategoria[]>) => {
                this.categorias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.produtoService.query().subscribe(
            (res: HttpResponse<IProduto[]>) => {
                this.produtos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.categoria.id !== undefined) {
            this.subscribeToSaveResponse(this.categoriaService.update(this.categoria));
        } else {
            this.subscribeToSaveResponse(this.categoriaService.create(this.categoria));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICategoria>>) {
        result.subscribe((res: HttpResponse<ICategoria>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCategoriaById(index: number, item: ICategoria) {
        return item.id;
    }

    trackProdutoById(index: number, item: IProduto) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get categoria() {
        return this._categoria;
    }

    set categoria(categoria: ICategoria) {
        this._categoria = categoria;
    }
}
