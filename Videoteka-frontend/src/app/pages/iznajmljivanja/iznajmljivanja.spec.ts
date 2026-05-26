import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Iznajmljivanja } from './iznajmljivanja';

describe('Iznajmljivanja', () => {
  let component: Iznajmljivanja;
  let fixture: ComponentFixture<Iznajmljivanja>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Iznajmljivanja],
    }).compileComponents();

    fixture = TestBed.createComponent(Iznajmljivanja);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
