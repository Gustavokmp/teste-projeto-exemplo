import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ListaDeDesejos } from 'app/shared/model/lista-de-desejos.model';
import { ListaDeDesejosService } from './lista-de-desejos.service';
import { ListaDeDesejosComponent } from './lista-de-desejos.component';
import { ListaDeDesejosDetailComponent } from './lista-de-desejos-detail.component';
import { ListaDeDesejosUpdateComponent } from './lista-de-desejos-update.component';
import { ListaDeDesejosDeletePopupComponent } from './lista-de-desejos-delete-dialog.component';
import { IListaDeDesejos } from 'app/shared/model/lista-de-desejos.model';

@Injectable({ providedIn: 'root' })
export class ListaDeDesejosResolve implements Resolve<IListaDeDesejos> {
    constructor(private service: ListaDeDesejosService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((listaDeDesejos: HttpResponse<ListaDeDesejos>) => listaDeDesejos.body));
        }
        return of(new ListaDeDesejos());
    }
}

export const listaDeDesejosRoute: Routes = [
    {
        path: 'lista-de-desejos',
        component: ListaDeDesejosComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.listaDeDesejos.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'lista-de-desejos/:id/view',
        component: ListaDeDesejosDetailComponent,
        resolve: {
            listaDeDesejos: ListaDeDesejosResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.listaDeDesejos.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'lista-de-desejos/new',
        component: ListaDeDesejosUpdateComponent,
        resolve: {
            listaDeDesejos: ListaDeDesejosResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.listaDeDesejos.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'lista-de-desejos/:id/edit',
        component: ListaDeDesejosUpdateComponent,
        resolve: {
            listaDeDesejos: ListaDeDesejosResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.listaDeDesejos.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const listaDeDesejosPopupRoute: Routes = [
    {
        path: 'lista-de-desejos/:id/delete',
        component: ListaDeDesejosDeletePopupComponent,
        resolve: {
            listaDeDesejos: ListaDeDesejosResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.listaDeDesejos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
