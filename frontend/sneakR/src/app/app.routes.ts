import { Routes } from '@angular/router';
import { HomeComponent } from './_components/home/home.component';
import { LoginComponent } from './_components/login/login.component';
import { SelectComponent } from './_components/select/select.component';
import { ResellComponent } from './_components/resell/resell.component';
import { WebshopComponent } from './_components/webshop/webshop.component';
import { PasswordComponent } from './_components/password/password.component';
import { RegisterComponent } from './_components/register/register.component';
import { ResellSellComponent } from './_components/resell-sell/resell-sell.component';
import { ResellUserComponent } from './_components/resell-user/resell-user.component';
import { ResellCartComponent } from './_components/resell-cart/resell-cart.component';
import { AboutComponent } from './_components/about/about.component';
import { ContactComponent } from './_components/contact/contact.component';
import { GYIKComponent } from './_components/gyik/gyik.component';
import { ProductsComponent } from './_components/products/products.component';
import { WebshopCartComponent } from './_components/webshop-cart/webshop-cart.component';
import { AdminComponent } from './_components/admin/admin.component';
import { ResellProductsComponent } from './_components/resell-products/resell-products.component';
import { ShoeControllerComponent } from './_components/shoe-controller/shoe-controller.component';

export const routes: Routes = [
    {path:'', redirectTo:'/login', pathMatch:'full'},
    {path:'login',  component:LoginComponent},
    {path:'home',  component:HomeComponent},
    {path:'select',  component:SelectComponent},
    {path:'resell', component:ResellComponent},
    {path:'webshop', component:WebshopComponent},
    {path:'password', component:PasswordComponent},
    {path:'register', component:RegisterComponent},
    {path:'resell-sell', component:ResellSellComponent},
    {path:'resell-user', component:ResellUserComponent},
    {path:'resell-cart', component:ResellCartComponent},
    {path:'about', component:AboutComponent},
    {path:'GYIK', component:GYIKComponent},
    {path:'contact', component:ContactComponent},
    {path: 'products', component:ProductsComponent},
    {path: 'webshop-cart', component:WebshopCartComponent},
    {path: 'admin', component:AdminComponent},
    {path: 'resell-products', component:ResellProductsComponent},
    {path: 'shoe-controller', component:ShoeControllerComponent}

];