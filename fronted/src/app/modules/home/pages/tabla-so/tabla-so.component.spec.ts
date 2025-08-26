import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaSoComponent } from './tabla-so.component';

describe('TablaSoComponent', () => {
  let component: TablaSoComponent;
  let fixture: ComponentFixture<TablaSoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TablaSoComponent]
    });
    fixture = TestBed.createComponent(TablaSoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
