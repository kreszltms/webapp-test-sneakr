import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WebshopnavbarComponent } from './webshopnavbar.component';

describe('WebshopnavbarComponent', () => {
  let component: WebshopnavbarComponent;
  let fixture: ComponentFixture<WebshopnavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WebshopnavbarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WebshopnavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
