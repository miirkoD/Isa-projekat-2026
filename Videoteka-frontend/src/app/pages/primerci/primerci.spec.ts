import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Primerci } from './primerci';

describe('Primerci', () => {
  let component: Primerci;
  let fixture: ComponentFixture<Primerci>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Primerci],
    }).compileComponents();

    fixture = TestBed.createComponent(Primerci);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
