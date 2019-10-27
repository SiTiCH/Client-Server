package Server;

public class BusinessLogic {
    private double a1;
    private double a2;
    private double b1;
    private double b2;
    private double c1;
    private double c2;
    private double v;
    private double h;
    private double p1;
    private double p2;
    private double s1;
    private double s2;
    double constants = 1.0/3.0;

    public String counting(String variable){
        if(check(variable)){
            String[] args = variable.split(" ");
            a1=Double.parseDouble(args[0]);
            b1=Double.parseDouble(args[1]);
            c1=Double.parseDouble(args[2]);
            a2=Double.parseDouble(args[3]);
            b2=Double.parseDouble(args[4]);
            c2=Double.parseDouble(args[5]);
            h =Double.parseDouble(args[6]);
            p1=c1+a1+b1;
            p2=c2+a2+b2;
            s1=  Math.sqrt(p1*(p1-a1)*(p1-b1)*(p1-c1));
            s2=  Math.sqrt(p2*(p2-a2)*(p2-b2)*(p2-c2));
            v = constants*h*(s1+s2+ Math.sqrt(s1*s2));
            String request = String.format("The volume of a truncated pyramid is equal to: %.4f units\n",v);
            return request;
        }
        return "Wrong data\n";
    }
    public boolean check(String variable){
        int accessOne =0;
        int accessTwo=0;
        String[] args = variable.split(" ");
        for(int i = 0;i<args.length;i++){
            if(Tryparse.tryParseDouble(args[i])){
                accessOne++;
            }
        }
        if (accessOne==7){
            for(int i=0;i<args.length;i++){
                if(Double.parseDouble(args[i])>0){
                    accessTwo++;
                }
            }
        }
        if (accessTwo==7 && accessOne==7){
            return true;
        }
        return false;
    }
}
