import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResellProductsComponent } from './resell-products.component';

describe('ResellProductsComponent', () => {
  let component: ResellProductsComponent;
  let fixture: ComponentFixture<ResellProductsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResellProductsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResellProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
