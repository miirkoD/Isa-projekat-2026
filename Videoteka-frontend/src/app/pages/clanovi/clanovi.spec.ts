import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Clanovi } from './clanovi';

describe('Clanovi', () => {
  let component: Clanovi;
  let fixture: ComponentFixture<Clanovi>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Clanovi],
    }).compileComponents();

    fixture = TestBed.createComponent(Clanovi);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
