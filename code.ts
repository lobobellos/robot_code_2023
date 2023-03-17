

function recurse(bool:boolean){
  if(bool){
    console.log(bool)
  }else{
    recurse(bool)
  }
}

recurse(true)

class Cake{
  flavor:string
  layers:number

  constructor(flavor:string,layers:number){
    this.flavor = flavor
    this.layers = layers
  }
}

