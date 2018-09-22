/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { ListaDeDesejosDetailComponent } from 'app/entities/lista-de-desejos/lista-de-desejos-detail.component';
import { ListaDeDesejos } from 'app/shared/model/lista-de-desejos.model';

describe('Component Tests', () => {
    describe('ListaDeDesejos Management Detail Component', () => {
        let comp: ListaDeDesejosDetailComponent;
        let fixture: ComponentFixture<ListaDeDesejosDetailComponent>;
        const route = ({ data: of({ listaDeDesejos: new ListaDeDesejos('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [ListaDeDesejosDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ListaDeDesejosDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ListaDeDesejosDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.listaDeDesejos).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
