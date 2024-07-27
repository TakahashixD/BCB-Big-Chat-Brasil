export class User {
    private name: string;
    private password: string;

    constructor(name: string, password: string) {
        this.name = name;
        this.password = password;
      }

      getName(){
        return this.name;
      }

      setName(name: string){
        this.name = name;
      }

      getPassword(){
        return this.password;
      }

      setPassword(password: string){
        return this.password;
      }
  }