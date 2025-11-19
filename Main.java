class MojeFronta<T> {
    private Object[] prvky;
    private int front; 
    private int rear;  
    private int kapacita;
    private int pocet;
    
   
    public MojeFronta() {
        this(10);
    }
    
    // Konstruktor 
    public MojeFronta(int kapacita) {
        this.kapacita = kapacita;
        this.prvky = new Object[kapacita];
        this.front = 0;
        this.rear = -1;
        this.pocet = 0;
    }
    
    // Přidání
    public void enqueue(T prvek) {
        if (pocet == kapacita) {
            zvetsiKapacitu();
        }
        rear = (rear + 1) % kapacita;
        prvky[rear] = prvek;
        pocet++;
    }
    
    // Odebrání
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Fronta je prázdná! Nelze odebrat prvek.");
        }
        
        @SuppressWarnings("unchecked")
        T odebranyPrvek = (T) prvky[front];
        prvky[front] = null; 
        front = (front + 1) % kapacita;
        pocet--;
        
        return odebranyPrvek;
    }
    
    // Nahlédnutí 
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Fronta je prázdná! Nelze nahlédnout na prvek.");
        }
        
        @SuppressWarnings("unchecked")
        T prvniPrvek = (T) prvky[front];
        return prvniPrvek;
    }
    
    // Kontrola
    public boolean isEmpty() {
        return pocet == 0;
    }
    
    // Počty
    public int size() {
        return pocet;
    }
    
    // Zvedšení 
    private void zvetsiKapacitu() {
        int novaKapacita = kapacita * 2;
        Object[] novePole = new Object[novaKapacita];
        
        // Kopírování
        for (int i = 0; i < pocet; i++) {
            int index = (front + i) % kapacita;
            novePole[i] = prvky[index];
        }
        
        prvky = novePole;
        kapacita = novaKapacita;
        front = 0;
        rear = pocet - 1;
    }
    
    // Vypis celé fronty
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Fronta je prázdná";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Fronta: [");
        
        for (int i = 0; i < pocet; i++) {
            int index = (front + i) % kapacita;
            sb.append(prvky[index]);
            if (i < pocet - 1) {
                sb.append(", ");
            }
        }
        
        sb.append("]");
        return sb.toString();
    }
}

// Hlavní
public class Main {
    public static void main(String[] args) {
        // Vytvoření 
        MojeFronta<String> fronta = new MojeFronta<>();
        
        System.out.println("=== TEST FRONTY ===");
        
        // Přidání 
        System.out.println("\nPřidávám studenty do fronty...");
        fronta.enqueue("Jan Novák");
        fronta.enqueue("Petra Svobodová");
        fronta.enqueue("Martin Král");
        fronta.enqueue("Lucie Dvořáková");
        
        System.out.println("Aktuální stav: " + fronta);
        System.out.println("Počet prvků ve frontě: " + fronta.size());
        System.out.println("První ve frontě: " + fronta.peek());
        
        // Odebírání
        System.out.println("\n--- Odebírání studentů z fronty ---");
        while (!fronta.isEmpty()) {
            String student = fronta.dequeue();
            System.out.println("Právě byl obsloužen: " + student);
            System.out.println("Zbývá ve frontě: " + fronta.size() + " studentů");
            System.out.println("Aktuální stav: " + fronta);
        }
        
        // Test
        System.out.println("\n--- Testování výjimek ---");
        try {
            fronta.dequeue();
        } catch (IllegalStateException e) {
            System.out.println("Výjimka při dequeue: " + e.getMessage());
        }
        
        try {
            fronta.peek();
        } catch (IllegalStateException e) {
            System.out.println("Výjimka při peek: " + e.getMessage());
        }
        
        
        System.out.println("\n--- Další test ---");
        MojeFronta<String> fronta2 = new MojeFronta<>(3);
        
        fronta2.enqueue("Karel");
        fronta2.enqueue("Anna");
        fronta2.enqueue("Tomáš");
        fronta2.enqueue("Eva"); 
        
        System.out.println("Fronta po automatickém zvětšení: " + fronta2);
        System.out.println("Velikost fronty: " + fronta2.size());
    }
}