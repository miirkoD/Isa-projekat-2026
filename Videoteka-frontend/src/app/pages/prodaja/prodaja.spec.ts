import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Prodaja } from './prodaja';

describe('Prodaja', () => {
  let component: Prodaja;
  let fixture: ComponentFixture<Prodaja>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Prodaja],
    }).compileComponents();

    fixture = TestBed.createComponent(Prodaja);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
