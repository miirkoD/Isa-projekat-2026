import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Filmovi } from './filmovi';

describe('Filmovi', () => {
  let component: Filmovi;
  let fixture: ComponentFixture<Filmovi>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Filmovi],
    }).compileComponents();

    fixture = TestBed.createComponent(Filmovi);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
