/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterTestModule } from '../../../test.module';
import { ListaDeDesejosComponent } from 'app/entities/lista-de-desejos/lista-de-desejos.component';
import { ListaDeDesejosService } from 'app/entities/lista-de-desejos/lista-de-desejos.service';
import { ListaDeDesejos } from 'app/shared/model/lista-de-desejos.model';

describe('Component Tests', () => {
    describe('ListaDeDesejos Management Component', () => {
        let comp: ListaDeDesejosComponent;
        let fixture: ComponentFixture<ListaDeDesejosComponent>;
        let service: ListaDeDesejosService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [ListaDeDesejosComponent],
                providers: []
            })
                .overrideTemplate(ListaDeDesejosComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ListaDeDesejosComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ListaDeDesejosService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ListaDeDesejos('123')],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.listaDeDesejos[0]).toEqual(jasmine.objectContaining({ id: '123' }));
        });
    });
});
