import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { AdminComponent } from './pages/admin/admin.component';
import { AuthGuard } from './auth/auth.guard';

export const routes: Routes = [
  {
      path: '', redirectTo: '/login', pathMatch: 'full'
  },
  {
      path: 'login', component: LoginComponent
  },
  {
      path: 'admin', component: AdminComponent, canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
