import { Component, signal} from '@angular/core';
import { inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../auth/auth.service';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  authService;
  router;
  constructor(){
    this.authService = inject(AuthService);
    this.router = inject(Router);
  
  }
  protected loginForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  })

  onSubmit(){
    if(this.loginForm.valid){
      let name = this.loginForm.value.name ?? '';
      let pass = this.loginForm.value.password ?? '';
      let user: User = new User(name, pass);
      this.authService.login(user);
      if(this.authService.isLoggedIn()){
        this.router.navigate(['/admin']);
      }
    }
  }

  hide = signal(true);
  clickEvent(event: MouseEvent) {
    this.hide.set(!this.hide());
    event.stopPropagation();
  }
}

