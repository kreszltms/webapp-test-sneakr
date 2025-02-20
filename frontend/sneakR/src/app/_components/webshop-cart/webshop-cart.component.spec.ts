import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WebshopCartComponent } from './webshop-cart.component';

describe('WebshopCartComponent', () => {
  let component: WebshopCartComponent;
  let fixture: ComponentFixture<WebshopCartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WebshopCartComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WebshopCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
