using Microsoft.EntityFrameworkCore;
using ROT.Models.Entities;

namespace ROT.Repositories.Data
{
    public class TireDbContext : DbContext
    {
        public DbSet<Tire> Tires { get; set; }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlite("Filename=../ROT.Repositories/Tire.db");
        }
    }
}