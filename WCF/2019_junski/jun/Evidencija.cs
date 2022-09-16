using System;
using System.Collection.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace Jun2019
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single, ConcurrencyMode = ConcurrencyMode.Multiple)]
    public class Evidencija : IEvidencija
    {
        public Dictionary<string, Radnik> Radnici { get; set; }
        public Evidencija()
        {
            Radnici = new Dictionary<string, Radnik>()
        }

        public List<RadniDan> GetDates(string name)
        {
            List<RadniDan> dani = new List<RadniDan>();
            if(Radnici.ContainsKey(name))
            {
                foreach(RadniDan rd in Radnici[name].RadniDani.Values)
                {
                    if(rd.Logout!= null)
                    {
                        dani.Add(rd);
                    }
                }
            }
            return dani;
        }

        public bool Login(string name)
        {
            if(!Radnici.ContainsKey(name)) { return false; }
            if(Radnici[name].RadniDani.ContainsKey(DateTime.Today)) { return false; } //vec je ulogovan
        
            RadniDan dan = new RadniDan();
            dan.Login = new Log() {
                TimeStamp = DateTime.Now,
                Success = true
            };

            dan.Logout = new Log() {
                TimeStamp = null,
                Success = false
            };

            Radnici[name].RadniDani.Add(DateTime.Today, dan);

            return true;
        }

        public bool Logout(string name)
        {
            if(!Radnici.ContainsKey(name)) { return false; }
            if(!Radnici[name].RadniDani.ContainsKey(DateTime.Today)) { return false; } //nije se ni ulogovao
            if(Radnici[name].RadniDani[DateTime.Today].Logout.TimeStamp != null) { return false; } //vec je izlogovan

            Radnici[name].RadniDani[DateTime.Today].Logout.TimeStamp = DateTime.Now;
            Radnici[name].RadniDani[DateTime.Today].Logout.Success = true;

            return true;
        }

        public bool Register(String name)
        {
            if(Radnici.ContainsKey(name)) return false; //vec je registrovan
            
            Radnici.Add(name, new Radnik() {
                Ime = name;
                RadniDani = new Dictionary<DateTime, RadniDan>()
            });

            return true;

        }

    }
}