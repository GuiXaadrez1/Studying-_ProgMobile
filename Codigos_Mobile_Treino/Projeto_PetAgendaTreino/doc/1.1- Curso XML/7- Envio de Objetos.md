# Introdução Enviando Objetos com Parcelable e Serializable (Java)

## 🤔 Por que não usar só putExtra()?

O Intent.putExtra("nome", valor) funciona bem com dados simples como String, int, boolean, mas não envia objetos inteiros como Usuario, Produto, etc., a não ser que eles implementem Parcelable ou Serializable.

- O que é Serializable?

Interface Java que marca uma classe como serializável: o sistema pode transformar o objeto em bytes para enviá-lo.

Vantagem: fácil de usar.
Desvantagem: mais lento e menos eficiente que Parcelable.

- O que é Parcelable?
Interface específica do Android. Mais rápida e eficiente, mas exige mais código.

Vantagem: mais performática.
Desvantagem: mais verbosa.

## Exemplo prático com Serializable

//Classe modelo: Usuario.java

public class Usuario implements Serializable {
    private String nome;
    private int idade;

    public Usuario(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
}

## Enviando objeto com Intent na MainActivity.java

Usuario usuario = new Usuario("Guilherme", 25);
Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
intent.putExtra("usuario", usuario);
startActivity(intent);

## Recebendo o objeto na SegundaActivity.java

Intent intent = getIntent();
Usuario usuario = (Usuario) intent.getSerializableExtra("usuario");

String nome = usuario.getNome();
int idade = usuario.getIdade();

## Agora o mesmo exemplo com Parcelable (mais usado)

//Classe Usuario.java com Parcelable

public class Usuario implements Parcelable {
    private String nome;
    private int idade;

    public Usuario(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    protected Usuario(Parcel in) {
        nome = in.readString();
        idade = in.readInt();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(nome);
        parcel.writeInt(idade);
    }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
}

## Envio do objeto Parcelable

Usuario usuario = new Usuario("Guilherme", 25);
Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
intent.putExtra("usuario", usuario);
startActivity(intent);

## Recepção do objeto Parcelable

Usuario usuario = getIntent().getParcelableExtra("usuario");

String nome = usuario.getNome();
int idade = usuario.getIdade();

## Comparativo rápido

| Recurso    | `Serializable`          | `Parcelable`                      |
| ---------- | ----------------------- | --------------------------------- |
| Tipo       | Interface Java          | Interface Android específica      |
| Velocidade | Mais lenta              | Mais rápida                       |
| Facilidade | Mais simples            | Mais detalhada, exige boilerplate |
| Uso ideal  | Testes, objetos simples | Produção, objetos grandes         |

## Cuidados

Sempre verifique se o objeto realmente implementa Serializable ou Parcelable.

Objetos null podem causar NullPointerException se não tratados.
